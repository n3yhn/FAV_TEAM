/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.hqmc.BO.EvaluationRecords;
import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.FORM.EvaluationRecordsForm;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;

/**
 *
 * @author binhnt53
 */
public class EvaluationRecordsDAOHE  extends GenericDAOHibernate<EvaluationRecords, Long> {
    private static HashMap<String, List> lstFactory = new HashMap();

  public static HashMap<String, List> getLstFactory() {
    return lstFactory;
  }

  public static void setLstFactory(HashMap<String, List> lstFactory) {
    EvaluationRecordsDAOHE.lstFactory = lstFactory;
  }
  private static Logger log = Logger.getLogger(EvaluationRecords.class);
  private List keyList = new ArrayList();
  private List valueList = new ArrayList();
  //removeCache
  
  
      /**
     *tim kiem theo fileId
     *
     * @param files
     * @return
     */
  public EvaluationRecords findFilesByFileId(Files files) {
        String hql = " from EvaluationRecords t where 1 = 1 ";
        List lstParam = new ArrayList();
        if (files.getFileId() != null && files.getFileId() > 0) {
            hql += " and t.fileId = ? ";
            lstParam.add(files.getFileId());
        }
        Query query = getSession().createQuery("select t " + hql + " order by t.evaluationRecordsId desc");
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }
        List<EvaluationRecords> lstResult = query.list();
        if (!lstResult.isEmpty()) {
            return lstResult.get(0);
        } else {
            return null;
        }
    }
  public static void removeCache(String type) {
    if (lstFactory == null) {
      return;
    }
    if (lstFactory.containsKey(type)) {
      lstFactory.remove(type);
    }
  }
  public EvaluationRecordsForm boToForm(EvaluationRecords bo) {
    EvaluationRecordsForm frm = new EvaluationRecordsForm(bo);
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

  public EvaluationRecordsDAOHE() {
    super(EvaluationRecords.class);
  }
  
}
