/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.hqmc.BO.QualityControlPlan;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;

/**
 *
 * @author GPCP_BINHNT53
 */
public class QualityControlPlanDAOHE extends GenericDAOHibernate<QualityControlPlan, Long> {

    private static HashMap<String, List> lstFactory = new HashMap();

    public static HashMap<String, List> getLstFactory() {
        return lstFactory;
    }

    public static void setLstFactory(HashMap<String, List> lstFactory) {
        QualityControlPlanDAOHE.lstFactory = lstFactory;
    }
    private static Logger log = Logger.getLogger(QualityControlPlanDAOHE.class);
    private List keyList = new ArrayList();
    private List valueList = new ArrayList();
    //removeCache

    public static void removeCache(String type) {
        if (lstFactory == null) {
            return;
        }
        if (lstFactory.containsKey(type)) {
            lstFactory.remove(type);
        }
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

    public QualityControlPlanDAOHE() {
        super(QualityControlPlan.class);
    }

    //==========================================================================
     /**
     * findIsItempObj
     * @param objId
     * @param originalId
     * @param version
     * @param isLastVersion
     * @return
     */
    public QualityControlPlan findIsItempObj(Long objId, Long originalId, Long version, boolean isLastVersion) {//140726
        QualityControlPlan bo = new QualityControlPlan();
        try {

            List lstParam = new ArrayList();
            String hql = "select t from QualityControlPlan t where 1=1";
            if (objId != null && objId > 0L) {
                hql += " and t.qualityControlPlanId = ?";
                lstParam.add(objId);
            }
            if (originalId != null && originalId > 0L) {
                hql += " and t.originalId = ?";
                lstParam.add(originalId);
            }
            if (version != null && version > 0L) {
                hql += " and t.version = ?";
                lstParam.add(version);
            }
            if (isLastVersion) {
                hql += " and t.lastIsTemp = ?";
                lstParam.add(1L);
            }
            Query query = getSession().createQuery(hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
            }
            List<QualityControlPlan> lstObj = null;
            lstObj = query.list();
            if (!lstObj.isEmpty()) {
                bo = lstObj.get(0);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return bo;
    }
  /**
     * getCountVersion
     * @param objId
     * @return
     */
    public Long getCountVersion(Long objId) {
        Long iresult = 0L;
        List lstParam = new ArrayList();
        String hql = "select count(t) from QualityControlPlan t where t.originalId = ? ";
        lstParam.add(objId);
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }
        iresult = Long.parseLong(query.uniqueResult().toString()) + 1L;
        return iresult;
    }
    /**
     * updateSetNotLastIsTemp
     *
     * @param objId
     * @return
     */
    public int updateSetNotLastIsTemp(Long objId) {
        try {
            String hql = " update QualityControlPlan t set t.lastIsTemp = 0 where t.originalId = ?";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, objId);
            return query.executeUpdate();
        } catch (Exception e) {
            log.error(e);
            return 0;
        }
    }
}
