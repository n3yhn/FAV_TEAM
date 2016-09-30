/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.TechnicalStandard;
import com.viettel.hqmc.FORM.TechnicalStandardForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import java.util.HashMap;
import org.hibernate.Query;
import com.viettel.common.util.LogUtil;

/**
 *
 * @author vtit_binhnt53
 */
public class TechnicalStandardDAOHE extends GenericDAOHibernate<TechnicalStandard, Long> {

    private static Logger log = Logger.getLogger(TechnicalStandard.class);
    private static HashMap<Long, List> lstFactory = new HashMap();
    private List keyList = new ArrayList();
    private List valueList = new ArrayList();
    private List lstStandard;

    /**
     *
     */
    public TechnicalStandardDAOHE() {
        super(TechnicalStandard.class);
    }

    /**
     *
     */
    public static void removeCache() {
        if (lstFactory == null) {
            return;
        }
        lstFactory.clear();
    }

    /**
     *
     * @param firstResult
     * @param maxResult
     * @return
     */
    public List<TechnicalStandard> findAll(int firstResult, int maxResult) {
        return this.findByCriteria(firstResult, maxResult);
    }

    /**
     *
     * @param stAddEditForm
     * @param bo
     */
    public void formToBO(TechnicalStandardForm stAddEditForm, TechnicalStandard bo) {
        if (stAddEditForm.getStandardType() != null) {
            bo.setStandardType(stAddEditForm.getStandardType());
        }

        if (stAddEditForm.getStandardCode() != null) {
            bo.setStandardCode(stAddEditForm.getStandardCode().trim());
        }
    }

    /**
     *
     * @param searchForm
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findTechnicalStandard(TechnicalStandardForm searchForm, int start, int count, String sortField) {
        String hql = " from TechnicalStandard t where t.isActive = 1 ";
        List lstParam = new ArrayList();
        if (searchForm != null) {
//            if (searchForm.getIsActive()!= null && searchForm.getIsActive() >= 0) {
//                hql += " and c.isActive = ?";
//                lstParam.add(searchForm.getIsActive().intValue());
//            }

            if (searchForm.getStandardType() != null) {
                hql += " and t.standardType = ? ";
                lstParam.add(searchForm.getStandardType());
            }
            if (searchForm.getStandardCode() != null && searchForm.getStandardCode().trim().length() > 0) {
                hql += " and lower(t.standardCode) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(searchForm.getStandardCode().trim()));
            }
            if (searchForm.getVietnameseName() != null && searchForm.getVietnameseName().trim().length() > 0) {
                hql += " and ( lower(t.vietnameseName) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(searchForm.getVietnameseName().trim()));
                hql += " or  lower(t.englishName) like ? ESCAPE '/' )";
                lstParam.add(StringUtils.toLikeString(searchForm.getVietnameseName().trim()));
            }
            if (searchForm.getPublishAgencyName() != null && searchForm.getPublishAgencyName().trim().length() > 0) {
                hql += " and lower(t.publishAgencyName) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(searchForm.getPublishAgencyName().trim()));
            }
            if (searchForm.getPublishDateFrom() != null) {
                hql += " and t.publishDate >= ?";
                lstParam.add(searchForm.getPublishDateFrom());
            }
            if (searchForm.getPublishDateTo() != null) {
                hql += " and t.publishDate <= ?";
                lstParam.add(searchForm.getPublishDateTo());
            }
            if (searchForm.getEffectiveDateFrom() != null) {
                hql += " and t.effectiveDate >= ?";
                lstParam.add(searchForm.getEffectiveDateFrom());
            }
            if (searchForm.getEffectiveDateTo() != null) {
                hql += " and t.effectiveDate <= ?";
                lstParam.add(searchForm.getEffectiveDateTo());
            }
            if (searchForm.getExpireDateFrom() != null) {
                hql += " and t.expireDate >= ?";
                lstParam.add(searchForm.getExpireDateFrom());
            }
            if (searchForm.getExpireDateTo() != null) {
                hql += " and t.expireDate <= ?";
                lstParam.add(searchForm.getExpireDateTo());
            }
        }
        Query countQuery = getSession().createQuery("select count(t.technicalStandardId) " + hql);
        Query query = getSession().createQuery("select t " + hql + " order by t.technicalStandardId desc");
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
     * check duplicate createform
     *
     * @param createForm
     * @return
     */
    public boolean isDuplicate(TechnicalStandardForm createForm) {
        if (createForm == null) {
            return false;
        }
        List lstParam = new ArrayList();

        String hql = "select count(c) from TechnicalStandard c where isActive = 1 ";

        if (createForm.getTechnicalStandardId() != null && createForm.getTechnicalStandardId() > 0l) {
            hql += " and c.technicalStandardId <> ? ";
            lstParam.add(createForm.getTechnicalStandardId());
        }

        if (createForm.getStandardCode() != null && createForm.getStandardCode().length() > 0) {
            hql += " and lower(c.standardCode) = ?";
            lstParam.add(createForm.getStandardCode().toLowerCase());
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
     * tim kiem technical standard by standard code -- hieptq
     *
     * @param StandardCode
     * @return
     */
    public boolean findStandardByCode(String StandardCode) {

        List lstParam = new ArrayList();

        String hql = "select count(c) from TechnicalStandard c where isActive = 1 ";

        if (StandardCode.length() > 0) {
            hql += " and c.standardCode = ?";
            lstParam.add(StandardCode);
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
     * findAllStandard
     *
     * @return
     */
    public List findAllStandard() {

        try {

            StringBuilder stringBuilder = new StringBuilder(" from TechnicalStandard t ");
            stringBuilder.append("where t.isActive = 1 and t.status=1 and t.standardType = 0 and (t.effectiveDate <= sysdate) and (t.expireDate >= sysdate) order by t.technicalStandardId asc");
            Query query = getSession().createQuery(stringBuilder.toString());

            lstStandard = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<>();
        }
        return lstStandard;
    }

    /*
     * ==========================================================set get method
     */
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
}
