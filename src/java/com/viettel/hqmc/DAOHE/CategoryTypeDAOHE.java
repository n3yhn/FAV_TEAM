/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.LogUtil;
import com.viettel.hqmc.BO.CategoryType;
import com.viettel.hqmc.FORM.CategoryTypeForm;
import com.viettel.common.util.StringUtils;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;

/**
 *
 * @author vtit_binhnt53
 */
public class CategoryTypeDAOHE extends GenericDAOHibernate<CategoryType, Long> {

    private static HashMap<String, List> lstFactory = new HashMap();

    public static HashMap<String, List> getLstFactory() {
        return lstFactory;
    }

    public static void setLstFactory(HashMap<String, List> lstFactory) {
        CategoryTypeDAOHE.lstFactory = lstFactory;
    }
    private static Logger log = Logger.getLogger(CategoryType.class);
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

    /*
     *
     */

    /**
     * tim kiem category
     *
     * @param form
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findCategoryType(CategoryTypeForm form, int start, int count, String sortField) {
        String hql = " from CategoryType c where isActive = 1 ";
        List lstParam = new ArrayList();
        if (form != null) {
            if (form.getCategoryName() != null && form.getCategoryName().trim().length() > 0) {
                hql += " and lower(c.categoryName) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getCategoryName().trim()));
            }
            if (form.getCategoryType() != null && form.getCategoryType().trim().length() > 0) {
                hql += " and lower(c.categoryType) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getCategoryType().trim()));
            }
        }

        Query countQuery = getSession().createQuery("select count(*) " + hql);
        Query query = getSession().createQuery("select c " + hql + " order by c.categoryTypeId desc");
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

    /*
     * 
     */

    /**
     * tim kiem category
     *
     * @return
     */
    public List findAllCategoryType() {
        if (lstFactory == null) {
//            lstFactory = new HashMap();//sonar
        }

        if (lstFactory.containsKey("type")) {
            return lstFactory.get("type");
        }

        List<CategoryType> lstCategoryType = null;
//        List<CategorySearchForm> lstResult = new ArrayList();
        try {
            StringBuilder stringBuilder = new StringBuilder(" from CategoryType a ");
            stringBuilder.append("  where a.isActive = 1 "
                    + " order by nlssort(lower(ltrim(a.categoryName)),'nls_sort = Vietnamese') ");
            Query query = getSession().createQuery(stringBuilder.toString());
            lstCategoryType = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return new ArrayList<CategoryType>();
        }
//        lstCategoryFactory.put(type, lstResult);
        lstFactory.put("type", lstCategoryType);

        return lstCategoryType;
    }

    //kiem tra doi tuong co trung khong   
    /**
     * kiem tra doi tuong co bi trung k
     *
     * @param form
     * @return
     */
    public boolean isDuplicate(CategoryTypeForm form) {
        if (form == null) {
            return false;
        }
        List lstParam = new ArrayList();
        String hql = "select count(c) from CategoryType c where c.isActive = 1 ";
        if (form.getCategoryTypeId() != null && form.getCategoryTypeId() > 0l) {
            hql += " and c.categoryTypeId <> ? ";
            lstParam.add(form.getCategoryTypeId());
        }
        if (form.getCategoryName() != null && form.getCategoryName().trim().length() > 0) {
            hql += " and lower(c.categoryName) = ?";
            lstParam.add(form.getCategoryName().toLowerCase());
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

    public CategoryTypeForm boToForm(CategoryType bo) {
        CategoryTypeForm frm = new CategoryTypeForm(bo);
        return frm;
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

    public CategoryTypeDAOHE() {
        super(CategoryType.class);
    }
}
