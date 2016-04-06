/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.VietnameseStandard;
import com.viettel.hqmc.FORM.VietnameseStandardForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;

/**
 * Tieu chuan ki thuat viet nam ve vsattp
 * @author vtit_binhnt53
 */
public class VietnameseStandardDAOHE extends GenericDAOHibernate<VietnameseStandard, Long> {

  private static HashMap<String, List> lstFactory = new HashMap();
  private static Logger log = Logger.getLogger(VietnameseStandard.class);
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
     * @param searchForm
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findVietnameseStandard(VietnameseStandardForm searchForm, int start, int count, String sortField) {
//        String hql = " from VietnameseStandard v where v.isActive = 1 ";
    String hql = " from VietnameseStandard v where 1 = 1 ";
    List lstParam = new ArrayList();
    if (searchForm != null) {
      if (searchForm.getStandardCode() != null && searchForm.getStandardCode().trim().length() > 0) {
        hql += " and lower(v.standardCode) like ? ESCAPE '/' ";
        lstParam.add(StringUtils.toLikeString(searchForm.getStandardCode().trim()));
      }
      if (searchForm.getVietnameseName() != null && searchForm.getVietnameseName().trim().length() > 0) {
        hql += " and lower(v.vietnameseName) like ? ESCAPE '/' ";
        lstParam.add(StringUtils.toLikeString(searchForm.getVietnameseName().trim()));
      }
      if (searchForm.getPublishAgencyName() != null && searchForm.getPublishAgencyName().trim().length() > 0) {
        hql += " and lower(v.publishAgencyName) like ? ESCAPE '/' ";
        lstParam.add(StringUtils.toLikeString(searchForm.getPublishAgencyName().trim()));
      }
      if (searchForm.getPublishDateFrom() != null) {
        hql += " and v.publishDate >= ?";
        lstParam.add(searchForm.getPublishDateFrom());
      }
      if (searchForm.getPublishDateTo() != null) {
        hql += " and v.publishDate <= ?";
        lstParam.add(searchForm.getPublishDateTo());
      }
      if (searchForm.getEffectiveDateFrom() != null) {
        hql += " and v.effectiveDate >= ?";
        lstParam.add(searchForm.getEffectiveDateFrom());
      }
      if (searchForm.getEffectiveDateTo() != null) {
        hql += " and v.effectiveDate <= ?";
        lstParam.add(searchForm.getEffectiveDateTo());
      }
      if (searchForm.getExpireDateFrom() != null) {
        hql += " and v.expireDate >= ?";
        lstParam.add(searchForm.getExpireDateFrom());
      }
      if (searchForm.getExpireDateTo() != null) {
        hql += " and v.expireDate <= ?";
        lstParam.add(searchForm.getExpireDateTo());
      }

    }

    Query countQuery = getSession().createQuery("select count(v.vietnameseStandardId) " + hql);
    Query query = getSession().createQuery("select v " + hql + " order by v.vietnameseStandardId desc");
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
     * @return
     */
    public boolean isDuplicate(VietnameseStandardForm form) {
    if (form == null) {
      return false;
    }
    List lstParam = new ArrayList();
    String hql = "select count(v.vietnameseStandardId) from VietnameseStandard v where v.isActive = 1 ";
    if (form.getVietnameseStandardId() != null && form.getVietnameseStandardId() > 0l) {
      hql += " and v.vietnameseStandardId <> ? ";
      lstParam.add(form.getVietnameseStandardId());
    }
    if (form.getStandardCode() != null && form.getStandardCode().trim().length() > 0) {
      hql += " and lower(v.standardCode) = ?";
      lstParam.add(form.getStandardCode().toLowerCase());
    }
    if (form.getVietnameseName() != null && form.getVietnameseName().trim().length() > 0) {
      hql += " and lower(v.vietnameseName) = ?";
      lstParam.add(form.getStandardCode().toLowerCase());
    }
    //todo code check duplicate
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
  //CHECK EXIST

    /**
     *
     * @param stAddEditForm
     * @param bo
     */
    public void formToBO(VietnameseStandardForm stAddEditForm, VietnameseStandard bo) {
  }

    /**
     *
     * @param bo
     * @return
     */
    public VietnameseStandardForm boToForm(VietnameseStandard bo) {
    VietnameseStandardForm frm = new VietnameseStandardForm(bo);
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

  public VietnameseStandardDAOHE() {
    super(VietnameseStandard.class);
  }
}
