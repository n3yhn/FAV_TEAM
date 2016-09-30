/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.LogUtil;
import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.ConfirmImportSatistPaper;
import com.viettel.hqmc.FORM.ConfirmImportSatistPaperForm;
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
public class ConfirmImportSatistPaperDAOHE extends GenericDAOHibernate<ConfirmImportSatistPaper, Long> {

    private static HashMap<String, List> lstFactory = new HashMap();

    public static HashMap<String, List> getLstFactory() {
        return lstFactory;
    }

    public static void setLstFactory(HashMap<String, List> lstFactory) {
        ConfirmImportSatistPaperDAOHE.lstFactory = lstFactory;
    }
    private static Logger log = Logger.getLogger(ConfirmImportSatistPaper.class);
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
     * findConfirmImportSatistPaper
     *
     * @param form
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findConfirmImportSatistPaper(ConfirmImportSatistPaperForm form, int start, int count, String sortField) {
//        String hql = " from ConfirmImportSatistPaper t where 1 = 1 ";
        String hql = " from ConfirmImportSatistPaper t where isActive = 1 ";
        List lstParam = new ArrayList();
        if (form != null) {
            //code filter here
            if (form.getProductName() != null && form.getProductName().trim().length() > 0) {
                hql += " and lower(t.productName) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getProductName().trim()));
            }
            if (form.getProductCode() != null && form.getProductCode().trim().length() > 0) {
                hql += " and lower(t.productCode) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getProductCode().trim()));
            }
            if (form.getImportBusinessName() != null && form.getImportBusinessName().trim().length() > 0) {
                hql += " and lower(t.importBusinessName) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getImportBusinessName().trim()));
            }
            if (form.getExportBusinessName() != null && form.getExportBusinessName().trim().length() > 0) {
                hql += " and lower(t.exportBusinessName) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getExportBusinessName().trim()));
            }
        }

        Query countQuery = getSession().createQuery("select count(t.confirmImportSatistPaperId ) " + hql);
        Query query = getSession().createQuery("select t " + hql + " order by t.confirmImportSatistPaperId  desc");
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
     * findConfirmImportSatistPaper
     *
     * @return
     */
    public List findAllConfirmImportSatistPaper() {
        if (lstFactory == null) {
            lstFactory = new HashMap();
        }

        if (lstFactory.containsKey("type")) {
            return lstFactory.get("type");
        }

        List<ConfirmImportSatistPaper> lstConfirmImportSatistPaper = null;
//        List<CategorySearchForm> lstResult = new ArrayList();
        try {
            StringBuilder stringBuilder = new StringBuilder(" from ConfirmImportSatistPaper a ");
            stringBuilder.append("  where a.isActive = 1 "
                    + " order by nlssort(lower(ltrim(a.confirmImportSatistPaperId)),'nls_sort = Vietnamese') ");
            Query query = getSession().createQuery(stringBuilder.toString());
            lstConfirmImportSatistPaper = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return new ArrayList<ConfirmImportSatistPaper>();
        }
//        lstCategoryFactory.put(type, lstResult);
        lstFactory.put("type", lstConfirmImportSatistPaper);

        return lstConfirmImportSatistPaper;
    }

    //kiem tra doi tuong co trung khong   
    public boolean isDuplicate(ConfirmImportSatistPaperForm form) {
        if (form == null) {
            return false;
        }
        List lstParam = new ArrayList();
        String hql = "select count(t) from ConfirmImportSatistPaper t where 1 = 1 ";
        if (form.getConfirmImportSatistPaperId() != null && form.getConfirmImportSatistPaperId() > 0l) {
            hql += " and t.confirmImportSatistPaperId <> ? ";
            lstParam.add(form.getConfirmImportSatistPaperId());
        }
        if (form.getProductName() != null && form.getProductName().trim().length() > 0) {
            hql += " and t.productName = ? ";
            lstParam.add(form.getProductName());
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

    /*
     * 
     */

    public void formToBO(ConfirmImportSatistPaperForm stAddEditForm, ConfirmImportSatistPaper bo) {
    }

    public ConfirmImportSatistPaperForm boToForm(ConfirmImportSatistPaper bo) {
        ConfirmImportSatistPaperForm frm = new ConfirmImportSatistPaperForm();
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

    public ConfirmImportSatistPaperDAOHE() {
        super(ConfirmImportSatistPaper.class);
    }
}
