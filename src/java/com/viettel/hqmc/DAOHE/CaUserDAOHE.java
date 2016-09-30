/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.LogUtil;
import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.CaUser;
import com.viettel.hqmc.FORM.CaUserForm;
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
 * @author BINHNT53
 */
public class CaUserDAOHE extends GenericDAOHibernate<CaUser, Long> {

    private static HashMap<String, List> lstFactory = new HashMap();

    public static HashMap<String, List> getLstFactory() {
        return lstFactory;
    }

    public static void setLstFactory(HashMap<String, List> lstFactory) {
        CaUserDAOHE.lstFactory = lstFactory;
    }
    private static Logger log = Logger.getLogger(CaUser.class);
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

    /**
     * tim kiem ca user
     *
     * @return
     */
    public List findAllCaUser() {
        if (lstFactory == null) {
            lstFactory = new HashMap();
        }

        if (lstFactory.containsKey("type")) {
            return lstFactory.get("type");
        }

        List<CaUser> lstCaUser = null;
//        List<CategorySearchForm> lstResult = new ArrayList();
        try {
            StringBuilder stringBuilder = new StringBuilder(" from CaUser a ");
            stringBuilder.append("  where a.isActive = 1 "
                    + " order by nlssort(lower(ltrim(a.categoryName)),'nls_sort = Vietnamese') ");
            Query query = getSession().createQuery(stringBuilder.toString());
            lstCaUser = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return new ArrayList<CaUser>();

        }
//        lstCategoryFactory.put(type, lstResult);
        lstFactory.put("type", lstCaUser);

        return lstCaUser;
    }

    /**
     * check trung ca
     *
     * @param serialNumber
     * @return
     */
    public boolean checkCaSerial(String serialNumber) {
        try {
            if (serialNumber != null && serialNumber.trim().length() > 0) {
                String hql = " from CaUser a where a.caSerial = ? and a.status = 1";
                Query query = getSession().createQuery("select a " + hql);
                //query.setParameter(0, userName);
                query.setParameter(0, serialNumber);
                List<CaUser> lstResult = query.list();
                if (!lstResult.isEmpty()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }

        return false;
    }

    /**
     * check ca
     *
     * @param userName
     * @param serialNumber
     * @return
     */
    public boolean checkCaUser(String userName, String serialNumber) {
        try {
            if (userName != null && userName.trim().length() > 0 && serialNumber != null && serialNumber.trim().length() > 0) {
                String hql = " from CaUser a where a.userName = ? and a.caSerial = ? ";
                Query query = getSession().createQuery("select a " + hql);
                query.setParameter(0, userName);
                query.setParameter(1, serialNumber);
                List<CaUser> lstResult = query.list();
                if (!lstResult.isEmpty()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }

        return false;
    }

    /**
     * tim kiem ca
     *
     * @param form
     * @param start
     * @param count
     * @param sortField
     * @param userLogin
     * @return
     */
    public GridResult findCaUser(CaUserForm form, int start, int count, String sortField, String userLogin) {
        String hql = " from CaUser c where 1 = 1 ";
        List lstParam = new ArrayList();
        if (form != null) {
            if (userLogin != null && userLogin.trim().length() > 0) {
                hql += " and c.userName = ? and c.status = 1 ";
                lstParam.add(userLogin.trim());
            }
            if (form.getCaSerial() != null && form.getCaSerial().trim().length() > 0) {
                hql += " and lower(c.caSerial) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getCaSerial().trim()));
            }
        }

        Query countQuery = getSession().createQuery("select count(*) " + hql);
        //Query query = getSession().createQuery("select c " + hql + " order by c.caUserId desc");
        Query query = getSession().createQuery("select distinct c " + hql);
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

    public CaUserForm boToForm(CaUser bo) {
        CaUserForm frm = new CaUserForm(bo);
        return frm;
    }

    /**
     * tim kiem ca theo serial
     *
     * @param serial
     * @return
     */
    public List<CaUser> findCaUserBySerial(String serial) {

        List<CaUser> lstCaUser = null;
//        List<CategorySearchForm> lstResult = new ArrayList();
        try {
            String hql = " from CaUser a where a.businessId in (select b.businessId from CaUser b where b.caSerial = ? )";
            Query query = getSession().createQuery("select a " + hql);
            query.setParameter(0, serial);
            lstCaUser = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return new ArrayList<CaUser>();
        }
//        lstCategoryFactory.put(type, lstResult);
        return lstCaUser;
    }
//a 16 07 29

    public List<CaUser> findCaUserBySerialUser(String serial, String userName) {

        List<CaUser> lstCaUser = null;
        try {
            String hql = " from CaUser a where a.userName = ? "
                    + " and a.caSerial = ?";
            Query query = getSession().createQuery("select a " + hql);
            query.setParameter(0, userName);
            query.setParameter(1, serial);
            lstCaUser = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return new ArrayList<CaUser>();
        }
//        lstCategoryFactory.put(type, lstResult);
        return lstCaUser;
    }
//!a 16 07 29

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

    public CaUserDAOHE() {
        super(CaUser.class);
    }
}
