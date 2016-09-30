/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.Constants;
import com.viettel.hqmc.BO.StandardProduct;
import com.viettel.hqmc.FORM.StandardProductForm;
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
public class StandardProductDAOHE extends GenericDAOHibernate<StandardProduct, Long> {

    private static HashMap<String, List> lstFactory = new HashMap();
    private static Logger log = Logger.getLogger(StandardProduct.class);
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

    //FIND ALL 
    /**
     * findAllCategory
     *
     * @param type
     * @return
     */
    public List findAllCategory(String type) {
        if (lstFactory == null) {
            lstFactory = new HashMap();
        }

        if (lstFactory.containsKey(type)) {
            return lstFactory.get(type);
        }

        List<StandardProduct> lstCategory = null;
//        List<CategorySearchForm> lstResult = new ArrayList();
        try {
            if (type != null) {
                StringBuilder stringBuilder = new StringBuilder(" from StandardProduct c ");
                stringBuilder.append("  where c.isActive = ? "
                        + " order by nlssort(lower(ltrim(a.name)),'nls_sort = Vietnamese') ");
                Query query = getSession().createQuery(stringBuilder.toString());
                query.setParameter(0, Constants.Status.ACTIVE);
                query.setParameter(1, type);
                lstCategory = query.list();
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<StandardProduct>();
        }
        lstFactory.put(type, lstCategory);

        return lstCategory;
    }
    //checkbo

    public StandardProduct checkBO(StandardProduct bo) {
        try {
            StringBuilder strBuf = new StringBuilder(" from StandardProduct a ");
            strBuf.append(" where a.isActive = :isActive ");

//            if (bo.getCategoryId() != null) {
//                strBuf.append(" and a.categoryId != :categoryId ");
//            }
//            if (bo.getType() != null) {
//                strBuf.append(" and a.type = :type ");
//            }
//            if (bo.getCreatedBy() != null) {
//                strBuf.append(" and a.createdBy = :createdBy ");
//            }
//
//            if (bo.getCode() != null && bo.getName() != null) {
//                strBuf.append(" and (a.code = :code  or a.name = :name)");
//            }
//
//            Query query = getSession().createQuery(strBuf.toString());
//            query.setParameter("isActive", Constants.Status.ACTIVE);
//
//            if (bo.getCategoryId() != null) {
//                query.setParameter("categoryId", bo.getCategoryId());
//            }
//            if (bo.getType() != null) {
//                query.setParameter("type", bo.getType().trim());
//            }
//            if (bo.getCreatedBy() != null) {
//                query.setParameter("createdBy", bo.getCreatedBy());
//            }
//            if (bo.getCode() != null && bo.getName() != null) {
//                query.setParameter("code", bo.getCode().trim());
//                query.setParameter("name", bo.getName().trim());
//            }
//
//
//            List<Category> lstCategory = query.list();
//            if (!lstCategory.isEmpty()) {
//                return lstCategory.get(0);
//            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

        return null;
    }
    //CHECK EXIST

    public Boolean checkStExisted(String categoryTypeId) {
        keyList.add("categoryTypeId");
        valueList.add(categoryTypeId);
        return this.isExistIDInDb(keyList, valueList);
    }

    public void formToBO(StandardProductForm stAddEditForm, StandardProduct bo) {
    }

    public StandardProductForm boToForm(StandardProduct bo) {
        StandardProductForm frm = new StandardProductForm(bo);
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

    public StandardProductDAOHE() {
        super(StandardProduct.class);
    }
}
