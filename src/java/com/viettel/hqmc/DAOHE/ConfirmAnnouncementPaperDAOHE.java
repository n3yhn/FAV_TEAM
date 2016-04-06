/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.ConfirmAnnouncementPaper;
import com.viettel.hqmc.FORM.ConfirmAnnouncementPaperForm;
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
public class ConfirmAnnouncementPaperDAOHE extends GenericDAOHibernate<ConfirmAnnouncementPaper, Long> {
    private static HashMap<String, List> lstFactory = new HashMap();

    public static HashMap<String, List> getLstFactory() {
        return lstFactory;
    }

    public static void setLstFactory(HashMap<String, List> lstFactory) {
        ConfirmAnnouncementPaperDAOHE.lstFactory = lstFactory;
    }
    private static Logger log = Logger.getLogger(ConfirmAnnouncementPaper.class);
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
     *find all filter by BoForm
     */
    
    /**
     * find all filter by BoForm
     *
     * @param form
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findConfirmAnnouncementPaper(ConfirmAnnouncementPaperForm form, int start, int count, String sortField) {
        //String hql = " from ConfirmAnnouncementPaper t where 1 = 1 ";
        String hql = " from ConfirmAnnouncementPaper t where isActive = 1 ";
        List lstParam = new ArrayList();
        if (form != null) {
            //code filter here
            if (form.getConfirmationNo()!= null && form.getConfirmationNo().trim().length() > 0) {
                hql += " and lower(t.confirmationNo) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getConfirmationNo().trim()));
            }
            if (form.getDateOfIssue()!= null) {
                hql += " and t.dateOfIssue = ?";
                lstParam.add(form.getDateOfIssue());
            }
        }

        Query countQuery = getSession().createQuery("select count(t.confirmAnnouncementPaperId ) " + hql);
        Query query = getSession().createQuery("select t " + hql + " order by t.confirmAnnouncementPaperId  desc");
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
     * findAllConfirmAnnouncementPaper
     *
     * @return
     */
    public List findAllConfirmAnnouncementPaper() {
        if (lstFactory == null) {
            lstFactory = new HashMap();
        }

        if (lstFactory.containsKey("type")) {
            return lstFactory.get("type");
        }

        List<ConfirmAnnouncementPaper> lstConfirmAnnouncementPaper = null;
//        List<CategorySearchForm> lstResult = new ArrayList();
        try {
            StringBuilder stringBuilder = new StringBuilder(" from ConfirmAnnouncementPaper t ");
            stringBuilder.append("  where t.isActive = 1 "
                    + " order by nlssort(lower(ltrim(t.confirmAnnouncementPaperId)),'nls_sort = Vietnamese') ");
            Query query = getSession().createQuery(stringBuilder.toString());
            lstConfirmAnnouncementPaper = query.list();

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ArrayList<ConfirmAnnouncementPaper>();
        }
//        lstCategoryFactory.put(type, lstResult);
        lstFactory.put("type", lstConfirmAnnouncementPaper);

        return lstConfirmAnnouncementPaper;
    }

    //kiem tra doi tuong co trung khong   
    
    public boolean isDuplicate(ConfirmAnnouncementPaperForm form) {
        if (form == null) {
            return false;
        }
        List lstParam = new ArrayList();
        String hql = "select count(t) from ConfirmAnnouncementPaper t where t.isActive = 1 ";
//        String hql = "select count(t) from ConfirmAnnouncementPaper t where 1 = 1 ";
        if (form.getConfirmAnnouncementPaperId() != null && form.getConfirmAnnouncementPaperId() > 0l) {
            hql += " and t.confirmAnnouncementPaperId <> ? ";
            lstParam.add(form.getConfirmAnnouncementPaperId());
        }
        if (form.getConfirmationNo()!= null && form.getConfirmationNo().trim().length() > 0) {
            hql += " and t.confirmationNo = ? ";
            lstParam.add(form.getConfirmationNo());
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
    public void formToBO(ConfirmAnnouncementPaperForm stAddEditForm, ConfirmAnnouncementPaper bo) {
    }

    public ConfirmAnnouncementPaperForm boToForm(ConfirmAnnouncementPaper bo) {
        ConfirmAnnouncementPaperForm frm = new ConfirmAnnouncementPaperForm();
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

    public ConfirmAnnouncementPaperDAOHE() {
        super(ConfirmAnnouncementPaper.class);
    }
}
