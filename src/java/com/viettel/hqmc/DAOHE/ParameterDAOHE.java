/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.LogUtil;
import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.Parameter;
import com.viettel.hqmc.FORM.ParameterForm;
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
public class ParameterDAOHE extends GenericDAOHibernate<Parameter, Long> {

  private static volatile HashMap<String, List> lstFactory = new HashMap();

  public static HashMap<String, List> getLstFactory() {
    return lstFactory;
  }

  public static void setLstFactory(HashMap<String, List> lstFactory) {
    ParameterDAOHE.lstFactory = lstFactory;
  }
  private static Logger log = Logger.getLogger(Parameter.class);
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
     * findParameter
     *
     * @param form
     * @param start
     * @param count
     * @param sortField
     * @return
     */
  public GridResult findParameter(ParameterForm form, int start, int count, String sortField) {
    String hql = " from Parameter t where t.isActive = 1 ";
    List lstParam = new ArrayList();
    if (form != null) {
      if (form.getParameterCode()!= null && form.getParameterCode().trim().length() > 0) {
        hql += " and lower(t.parameterCode) like ? ESCAPE '/' ";
        lstParam.add(StringUtils.toLikeString(form.getParameterCode().trim()));
      }
      if (form.getParameterName()!= null && form.getParameterName().trim().length() > 0) {
        hql += " and lower(t.parameterName) like ? ESCAPE '/' ";
        lstParam.add(StringUtils.toLikeString(form.getParameterName().trim()));
      }
    }

    Query countQuery = getSession().createQuery("select count(*) " + hql);
    Query query = getSession().createQuery("select t " + hql + " order by t.parameterId desc");
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
     * findAllParameter
     *
     * @return
     */
  public List findAllParameter() {
//    if (lstFactory == null) {
//      lstFactory = new HashMap();
//    }

    if (lstFactory.containsKey("type")) {
      return lstFactory.get("type");
    }

    List<Parameter> lstParameter = null;
//        List<CategorySearchForm> lstResult = new ArrayList();
    try {
      StringBuilder stringBuilder = new StringBuilder(" from Parameter t ");
      stringBuilder.append("  where t.isActive = 1 "
              + " order by nlssort(lower(ltrim(t.parameterName)),'nls_sort = Vietnamese') ");
      Query query = getSession().createQuery(stringBuilder.toString());
      lstParameter = query.list();

    } catch (Exception ex) {
      LogUtil.addLog(ex);//binhnt sonar a160901
      return new ArrayList<Parameter>();
    }
    lstFactory.put("type", lstParameter);

    return lstParameter;
  }

  //
      /**
     * kiem tra doi tuong co trung khong  
     *
     * @param form
     * @return
     */
  public boolean isDuplicate(ParameterForm form) {
    if (form == null) {
      return false;
    }
    List lstParam = new ArrayList();
    String hql = "select count(t) from Parameter t where t.isActive = 1 ";
    if (form.getParameterId() != null && form.getParameterId() > 0l) {
      hql += " and t.parameterId <> ? ";
      lstParam.add(form.getParameterId());
    }
    if (form.getParameterCode() != null && form.getParameterCode().trim().length() > 0) {
      hql += " and lower(t.parameterCode) = ?";
      lstParam.add(form.getParameterCode().toLowerCase());
    }
    if (form.getParameterName() != null && form.getParameterName().trim().length() > 0) {
      hql += " and lower(t.parameterName) = ?";
      lstParam.add(form.getParameterName().toLowerCase());
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

  public ParameterForm boToForm(Parameter bo) {
    ParameterForm frm = new ParameterForm(bo);
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

  public ParameterDAOHE() {
    super(Parameter.class);
  }
}
