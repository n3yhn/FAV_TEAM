/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.hqmc.BO.TestRegistration;
import com.viettel.hqmc.FORM.TestRegistrationForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import com.viettel.common.util.LogUtil;

/**
 *
 * @author vtit_binhnt53
 */
public class TestRegistrationDAOHE extends GenericDAOHibernate<TestRegistration, Long> {

    private volatile static HashMap<String, List> lstFactory = new HashMap();

    public static HashMap<String, List> getLstFactory() {
        return lstFactory;
    }

    public static void setLstFactory(HashMap<String, List> lstFactory) {
        TestRegistrationDAOHE.lstFactory = lstFactory;
    }
    private static Logger log = Logger.getLogger(TestRegistration.class);
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
     *
     * @param objId
     * @param originalId
     * @param version
     * @param isLastVersion
     * @return
     */
    public TestRegistration findIsItempObj(Long objId, Long originalId, Long version, boolean isLastVersion) {//140726
        TestRegistration bo = new TestRegistration();
        try {

            List lstParam = new ArrayList();
            String hql = "select t from TestRegistration t where 1=1";
            if (objId != null && objId > 0L) {
                hql += " and t.testRegistrationId = ?";
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
            List<TestRegistration> lstObj;
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
     * getCountVersion
     *
     * @param objId
     * @return
     */
    public Long getCountVersion(Long objId) {
        Long iresult;
        List lstParam = new ArrayList();
        String hql = "select count(t) from TestRegistration t where t.originalId = ? ";
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
            String hql = " update TestRegistration t set t.lastIsTemp = 0 where t.originalId = ?";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, objId);
            return query.executeUpdate();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return 0;
        }
    }

    /**
     * findTestRegistration
     *
     * @param form
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findTestRegistration(TestRegistrationForm form, int start, int count, String sortField) {
        String hql = " from TestRegistration t where 1 = 1 ";
        List lstParam = new ArrayList();
        if (form != null) {
            //filter by... code cont
//            if (form.getCategoryName() != null && form.getCategoryName().trim().length() > 0) {
//                hql += " and lower(c.categoryName) like ? escape'!'";
//                lstParam.add("%" + convertToLikeString(form.getCategoryName()) + "%");
//            }
//            if (form.getTestRegistration() != null && form.getTestRegistration().trim().length() > 0) {
//                hql += " and lower(c.categoryType) like ? escape'!'";
//                lstParam.add("%" + convertToLikeString(form.getTestRegistration()) + "%");
//            }
        }

        Query countQuery = getSession().createQuery("select count(t.testRegistrationId ) " + hql);
        Query query = getSession().createQuery("select t " + hql + " order by t.testRegistrationId  desc");
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

    /**
     * findAllTestRegistration
     *
     * @return
     */
    public List findAllTestRegistration() {
//        if (lstFactory == null) {//sonar
//            lstFactory = new HashMap();
//        }

        if (lstFactory.containsKey("type")) {
            return lstFactory.get("type");
        }

        List<TestRegistration> lstTestRegistration = null;
        try {
            StringBuilder stringBuilder = new StringBuilder(" from TestRegistration t ");
            stringBuilder.append("  where 1 = 1 "
                    + " order by nlssort(lower(ltrim(t.testRegistrationId)),'nls_sort = Vietnamese') ");
            Query query = getSession().createQuery(stringBuilder.toString());
            lstTestRegistration = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<TestRegistration>();
        }
        lstFactory.put("type", lstTestRegistration);

        return lstTestRegistration;
    }

    /**
     * check duplicate before insert into db
     *
     * @param form
     * @return
     */
    public boolean isDuplicate(TestRegistrationForm form) {
        if (form == null) {
            return false;
        }
        List lstParam = new ArrayList();
        String hql = "select count(t) from TestRegistration t where 1 = 1 ";
        if (form.getTestRegistrationId() != null && form.getTestRegistrationId() > 0l) {
            hql += " and r.testRegistrationId <> ? ";
            lstParam.add(form.getTestRegistrationId());
        }
        //condition cont
//        if (form.getBussinessTaxCode()!= null && form.getBussinessTaxCode().trim().length() > 0) {
//            hql += " and r.bussinessTaxCode = ? ";
//            lstParam.add(form.getBussinessTaxCode());
//        }
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
     * @param stAddEditForm
     * @param bo
     */
    public void formToBO(TestRegistrationForm stAddEditForm, TestRegistration bo) {
    }

    /**
     *
     * @param bo
     * @return
     */
    public TestRegistrationForm boToForm(TestRegistration bo) {
        TestRegistrationForm frm = new TestRegistrationForm(bo);
        return frm;
    }
//==============================================================================

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

    public TestRegistrationDAOHE() {
        super(TestRegistration.class);
    }
}
