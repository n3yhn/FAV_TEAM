/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.LogUtil;
import com.viettel.hqmc.BO.DetailProduct;
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
public class DetailProductDAOHE extends GenericDAOHibernate<DetailProduct, Long> {

    private static HashMap<String, List> lstFactory = new HashMap();

    public static HashMap<String, List> getLstFactory() {
        return lstFactory;
    }

    public static void setLstFactory(HashMap<String, List> lstFactory) {
        DetailProductDAOHE.lstFactory = lstFactory;
    }
    private static Logger log = Logger.getLogger(DetailProduct.class);
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

    public DetailProductDAOHE() {
        super(DetailProduct.class);
    }

    //==========================================================================
    /**
     * tim kiem sua doi bo sung
     *
     * @param objId
     * @param originalId
     * @param version
     * @param isLastVersion
     * @return
     */
    public DetailProduct findIsItempObj(Long objId, Long originalId, Long version, boolean isLastVersion) {//140726
        DetailProduct bo = new DetailProduct();
        try {

            List lstParam = new ArrayList();
            String hql = "select t from DetailProduct t where 1=1";
            if (objId != null && objId > 0L) {
                hql += " and t.detailProductId = ?";
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
            List<DetailProduct> lstObj = null;
            lstObj = query.list();
            if (!lstObj.isEmpty()) {
                bo = lstObj.get(0);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return bo;
    }

    /**
     * dem version
     *
     * @param objId
     * @return
     */
    public Long getCountVersion(Long objId) {
        Long iresult = 0L;
        List lstParam = new ArrayList();
        String hql = "select count(t) from DetailProduct t where t.originalId = ? ";
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
            String hql = " update DetailProduct t set t.lastIsTemp = 0 where t.originalId = ?";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, objId);
            return query.executeUpdate();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return 0;
        }
    }
}
